import Router from 'express'
import knex from '../database/knex'
import AppError from '../utils/AppError'
import { hash } from 'bcrypt'
import { z } from "zod";

const router = Router()

router.get('/', async (req, res) => {
    const usuarios = await knex('usuarios')

    res.json({ usuarios })
})

interface IDadosAgendamento {
    nome: String
    email: String
    login: String
    senha: String
    ativo: String

}

router.post('/', async (req, res) => {

    const objSalvar: IDadosAgendamento = req.body;

    // if (!objSalvar?.nome) {
    //     throw new AppError('Nome é obrigatorio!')
    // }

    const usuario = await knex("usuario")
        .insert(objSalvar)

    res.json({ message: "Usuário Salvo" })

})

router.put('/:id', async (req, res) => {
    const objSalvar = req.body
    const { id } = req.params

    if (objSalvar?.senha) {
        objSalvar.senha = await hash(objSalvar.senha, 8)
    }

    let usuario = await knex('usuarios').where({ id }).first()

    if (!usuario?.id) {
        throw new AppError('Usuário não encontrado')
    }

    let newUsuario = {
        ... usuario,
        ... objSalvar,
    }

    await knex('usuarios').update(newUsuario)
        .where({ id: usuario.id })

    return res.json({
        message: "Editado usuário com sucesso!!",
        usuario: usuario
    })
})

router.delete('/:id', async(req, res) => {
    const {id} = req.params

    let usuario = await knex('usuarios').where({ id }).first()

    if (!usuario?.id) {
        throw new AppError('Usuário não encontrado')
    }

    await knex('usuarios').where({id}).delete()

    return res.json({ message: "Usuário deeletado com sucesso"})

})

export default router
