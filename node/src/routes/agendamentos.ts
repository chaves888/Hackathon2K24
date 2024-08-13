import Router from 'express'
import knex from '../database/knex'
import AppError from '../utils/AppError'





const router = Router()

router.get('/:id', async (req, res) => {
    const { id } = req.params;

    try {
        // Busca o agendamento no banco de dados
        let agendamento = await knex('agendamento').where({ id }).first();

        // Verifica se o agendamento foi encontrado
        if (!agendamento) {
            return res.status(404).json({ error: 'Agendamento não encontrado' });
        }

        // Retorna o agendamento como JSON
        res.json({ agendamento });
    } catch (error) {
        // Tratamento de erros
        console.error(error);
        res.status(500).json({ error: 'Erro ao buscar o agendamento' });
    }
});


router.delete('/:id', async (req, res) => {
    const { id } = req.params;
    try {
        const result = await knex('agendamento').where({ id }).del();
        if (result) {
            res.status(200).send('Agendamento excluído com sucesso');
        } else {
            res.status(404).send('Agendamento não encontrado');
        }
    } catch (error) {
        console.error(error);
        res.status(500).send('Erro ao excluir o agendamento');
    }
});

export default router