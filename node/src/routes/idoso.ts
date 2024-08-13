import Router from 'express'
import knex from '../database/knex'
import AppError from '../utils/AppError'

const router = Router()

router.get('/', (req, res) => {
    knex("tabela_idoso").then((idoso) => {

        res.json({ idoso })
    })
})

interface IDadosIdoso{
 nome: String
 observacao: String
}

router.post('/', async (req, res) => {

    const objSalvar: IDadosIdoso = req.body;

    if (!objSalvar?.nome) {
        throw new AppError('Nome é obrigatorio!')
    }

    const nome_idoso = await knex("tabela_idoso")
        .insert(objSalvar)

    res.json({ message: "Categoria Salva" })

})

router.put('/:id', async (req, res) => {
    const objSalvar = req.body
    const { id } = req.params



    let idoso = await knex('tabela_idoso').where({ id }).first()

    if (!idoso?.id) {
        throw new AppError('idoso não encontrado')
    }


    let newAgente= {
        ... idoso,
        ... objSalvar,
    }

    await knex('tabela_idoso').update(newAgente)
        .where({ id: idoso.id })

    return res.json({
        message: "Editado idoso com sucesso!!",

    })
})

router.delete('/:id', async(req, res) => {
    const {id} = req.params

    let idoso = await knex('tabela_idoso').where({ id }).first()

    if (!idoso?.id) {
        throw new AppError('idoso não encontrada')
    }

    await knex('tabela_idoso').where({id}).delete()

    return res.json({ message: "idoso deletado com sucesso"})

})
export default router