<?php 

    $login = $_POST["login"] ?? NULL;
    $senha = $_POST["password"] ?? NULL;

    $naoTemLogin = !empty($login);
    $naoTemSenha = !empty($senha);

    if($naoTemLogin && $naoTemSenha) {

        $sql = "SELECT `id`, `nome`, `login`, `senha` FROM `usuario` WHERE `login` = :login AND `ativo` = 'S'";
        
        $consulta = $pdo->prepare($sql);
        $consulta->bindParam(":login", $login);
        $consulta->execute();
        $dados = $consulta->fetch(PDO::FETCH_OBJ);
        
        $IdNaoExiste = !isset($dados->id);
        $senhaInvalida = !password_verify($senha, $dados->senha);
        if($IdNaoExiste) {
            mensagemErro("Usuário não encontrado!");            
        }elseif($senhaInvalida){
                mensagemErro("Usuário ou senha incorretos!");
            }
            $_SESSION["usuario"] = [
                "id" => $dados->id,
                "nome" => $dados->nome,
                "login" => $dados->login
            ];
            echo "<script>window.location.href='paginas/home'</script>";
            exit;
        }

?>

<div>
    <form class="login" method="POST" action="">

    <h1>Faça seu login</h1>
    <label for="login">Login</label>
    <input type="text" class="form-control" placeholder="Digite o login" id="login" name="login" required>

    <label for="password">Senha</label>
    <input type="password" class="form-control" placeholder="Digite a senha" id="password" name="password" required>

    <input class="form-control" type="submit" value="Enviar">
    </form>
</div>