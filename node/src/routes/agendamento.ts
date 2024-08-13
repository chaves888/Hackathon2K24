import Router from 'express'
import knex from '../database/knex'
import AppError from '../utils/AppError'

const router = Router()

// router.get('/:id', (req, res) => {
//     knex("agendamento").then((agendamentos) => {

//         res.json({ agendamentos })
//     })
// })

router.get('/', (req, res) => {
    knex("agendamento").then((agendamentos) => {

        res.json({ agendamentos })
    })
})


interface IDadosAgendamento {
    agendamento: Date
    nome_idoso: String
    nome_agente: String
    nome_vacina: String
    status: String

}

router.post('/', async (req, res) => {

    const objSalvar: IDadosAgendamento = req.body;

    // if (!objSalvar?.nome) {
    //     throw new AppError('Nome é obrigatorio!')
    // }

    const agendamento = await knex("agendamento")
        .insert(objSalvar)

    res.json({ message: "Agendamento Salvo" })

})

router.put('/:id', async (req, res) => {
    const objSalvar = req.body
    const { id } = req.params



    let agendamento = await knex('agendamento').where({ id }).first()

    if (!agendamento?.id) {
        throw new AppError('Usuário não encontrado')
    }


    let newAgendamento = {
        ... agendamento,
        ... objSalvar,
    }

    await knex('agendamento').update(newAgendamento)
        .where({ id: agendamento.id })

    return res.json({
        message: "Editado usuário com sucesso!!",

    })
})

router.delete('/:id', async(req, res) => {
    const {id} = req.params

    let agendamento = await knex('agendamento').where({ id }).first()

    if (!agendamento?.id) {
        throw new AppError('agendamento não encontrada')
    }

    await knex('agendamento').where({id}).delete()

    return res.json({ message: "agendamento deletado com sucesso"})

})

export default router
