import Router from 'express'
import knex from '../database/knex'
import AppError from '../utils/AppError'

const router = Router()

router.get('/', (req, res) => {
    knex("vacina").then((vacinas) => {

        res.json({ vacinas })
    })
})

interface IDadosVacina {
 nome: String
}

router.post('/', async (req, res) => {

    const objSalvar: IDadosVacina = req.body;

    if (!objSalvar?.nome) {
        throw new AppError('Nome é obrigatorio!')
    }

    const vacina = await knex("vacina")
        .insert(objSalvar)

    res.json({ message: "Categoria Salva" })

})

router.put('/:id', async (req, res) => {
    const objSalvar = req.body
    const { id } = req.params

    let vacina = await knex('vacina').where({ id }).first()


    if (!vacina?.id) {
        throw new AppError('Vacina não encontrado')
    }

    let newVacina = {
        ... vacina,
        ... objSalvar,
    }

    console.log(newVacina);
    
    await knex('vacina').update(newVacina)
        .where({ id: vacina.id })

    return res.json({
        message: "Editado com sucesso!!"
        
    }   )
})

router.delete('/:id', async(req, res) => {
    const {id} = req.params

    let vacina = await knex('vacina').where({ id }).first()

    if (!vacina?.id) {
        throw new AppError('vacina não encontrada')
    }

    await knex('vacina').where({id}).delete()

    return res.json({ message: "vacina deletada com sucesso"})

})

export default router