import { Router } from 'express'
import usuarioRouter from './usuario'
import agendamentoRouter from './agendamento'
import agenteRouter from './agente'
import vacinaRouter from './vacina'
import idosoRouter from './idoso'
import agendamentosRouter from "./agendamentos"

const routes = Router()

routes.use("/agendamento",agendamentoRouter)
routes.use("/agente",agenteRouter)
routes.use("/vacina",vacinaRouter)
routes.use("/idoso",idosoRouter)
routes.use('/usuario', usuarioRouter)
routes.use('/agendamentos', agendamentosRouter)

export default routes
