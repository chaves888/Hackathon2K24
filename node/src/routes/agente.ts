import Router from 'express'
import knex from '../database/knex'
import AppError from '../utils/AppError'

const router = Router()

router.get('/', (req, res) => {
    knex("agente").then((agentes) => {

        res.json({ agentes })
    })
})

interface IDadosAgente {
 nome: String
}

router.post('/', async (req, res) => {

    const objSalvar: IDadosAgente = req.body;

    if (!objSalvar?.nome) {
        throw new AppError('Nome é obrigatorio!')
    }

    const agente = await knex("agente")
        .insert(objSalvar)

    res.json({ message: "Categoria Salva" })

})

router.put('/:id', async (req, res) => {
    const objSalvar = req.body
    const { id } = req.params



    let agente = await knex('agente').where({ id }).first()

    if (!agente?.id) {
        throw new AppError('agente não encontrado')
    }


    let newAgente= {
        ... agente,
        ... objSalvar,
    }

    await knex('agente').update(newAgente)
        .where({ id: agente.id })

    return res.json({
        message: "Editado agente com sucesso!!",

    })
})

router.delete('/:id', async(req, res) => {
    const {id} = req.params

    let agente = await knex('agente').where({ id }).first()

    if (!agente?.id) {
        throw new AppError('aagente não encontrada')
    }

    await knex('agente').where({id}).delete()

    return res.json({ message: "agente deletado com sucesso"})

})

export default router