import { Request, Response, NextFunction } from 'express'
import { verify } from "jsonwebtoken"
import AppError from "../utils/AppError"
import AuthConfig from '../configs/auth'


interface IToken {
  idUsuario : number
}
function autenticacao(
  req: Request,
  res: Response,
  next: NextFunction
) {
  const authHeader = req.headers.authorization

  if (!authHeader) {
    throw new AppError("Token inválido !", 401)
  }
  const [,token] = authHeader.split(" ")

  try {
    
    const dadosToken  = <IToken>verify(token, AuthConfig.jwt.secret)

    console.log('dadosToken')
    console.log(dadosToken)


    req.user =  {id : dadosToken.idUsuario}

    return next()
  } 
  catch (error) {
    throw new AppError("Token inválido !", 401)
  }

}

export default autenticacao