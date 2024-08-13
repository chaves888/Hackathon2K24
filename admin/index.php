<?php
require('./configs/config.php');
session_start();

?>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <link rel="icon" href="./img/icone.png">
    <base href="<?php echo "http://" . $_SERVER["HTTP_HOST"] . $_SERVER["SCRIPT_NAME"]; ?>">

    <!-- Bootst rap core JavaScript-->
    <script src="vendor/jquery/jquery.min.js"></script>

    <script src="js/lightbox-plus-jquery.min.js"></script>

    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <script type="text/javascript" src="vendor/summernote/summernote.min.js"></script>
    <script type="text/javascript" src="vendor/summernote/summernote-bs4.min.js"></script>
    <script src="vendor/summernote/lang/summernote-pt-BR.js"></script>
    <!-- Core plugin JavaScript-->
    <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="js/sb-admin-2.min.js"></script>


    <!-- Outros Javascript -->
    <script src="js/parsley.min.js"></script>

    <script src="js/jquery.inputmask.min.js"></script>
    <script src="js/bindings/inputmask.binding.js"></script>
    <script src="js/jquery.maskMoney.min.js"></script>
    <script src="vendor/datatables/jquery.dataTables.min.js"></script>
    <script src="vendor/datatables/dataTables.bootstrap4.min.js"></script>
    <script src="js/sweetalert2.min.js"></script>

    <!-- Custom fonts for this template-->
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="css/sb-admin-2.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <link rel="stylesheet" type="text/css" href="css/lightbox.min.css">

    <link rel="stylesheet" type="text/css" href="vendor/summernote/summernote.min.css">
    <link rel="stylesheet" type="text/css" href="vendor/summernote/summernote-bs4.min.css">
    <link rel="stylesheet" href="css/sweetalert2.min.css">
    </script>

</head>

<body id="page-top">
    <?php
    require "funcoes.php";
    // verificando se a sessão usuario existe
    $usuarioNaoExiste = !isset($_SESSION["usuario"]);

    // se o usuario nao existir entra no if e é encaminhado pro login
    if ($usuarioNaoExiste) {
        //Puxa o trecho de login php para dentro do IF
        require "paginas/login.php";
    } else {
        // verifica se existe algum parametro dentro da variavel global GET
        if (isset($_GET['param'])) {

            // 'paginas/home/1
            // paginas == pasta/diretorio
            // home = o arquivo php
            // $array['paginas','home','id']

            // explode está desmontando o array param, dividindo pela "/" 
            $page = explode("/", $_GET['param']);

            // adicionando as partes desmontadas em cada variável
            $pasta = $page[0] ?? NULL;
            $arquivo = $page[1] ?? NULL;
            $id = $page[2] ?? NULL;

            // montando a url da pagina
            $page = "$pasta/$arquivo";

            // verificando se existe o arquivo com o nome da variavel page
            if(file_exists("$page.php")) {
                require "$page.php";
            } else {
                // caso nao exista um arquivo com o nome page redireciona o usuario para uma pagina com mensagem de erro!
                require "paginas/error.php";
            }
        }
    }
    ?>
</body>

</html>