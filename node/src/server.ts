import express,
{
    Request,
    Response,
    NextFunction
} from 'express'
import 'express-async-errors'

import cors from 'cors'

import routes from './routes'

import AppError from './utils/AppError'
import { ZodError } from 'zod'

const app = express()
const methodOverride = require('method-override');
app.use(methodOverride('_method'));

app.use(cors())

app.use(express.json())

app.use(routes)



app.use((
    error: any,
    req: Request,
    res: Response,
    next: NextFunction
) => {

    if(error instanceof ZodError){
            return res.status(400)
            .send({message: "Erro de validaçao",
             issues: error.format()

             }         )
    }

    if (error instanceof AppError) {
        return res.status(error.statusCode).json({
            status: "Error",
            message: error.mensagem
        })
    }

    return res.status(500).json({
        status: 'error',
        message: 'Deu ruim'
    })
})

const PORT = 3001

app.listen(PORT, () => {
    console.log('Iniciado projeto na porta ' +
        PORT
    )
})
