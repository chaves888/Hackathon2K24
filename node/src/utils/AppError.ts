class AppError {
    mensagem
    statusCode

    constructor(mensagem:string, statusCode = 400) {
        this.mensagem = mensagem
        this.statusCode = statusCode
    }
}

export default AppError
