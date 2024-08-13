<!DOCTYPE html>
<html lang="pt-br">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Agendamentos Online de Vacinas</title>
    <link rel="stylesheet" href="./cssPages/home.css">
    <link rel="icon" href="./img/logovacina.png">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
    <script src="https://kit.fontawesome.com/8bb0dffdb3.js" crossorigin="anonymous"></script>
</head>

<body>
    <?php
    require "./header.php";
    ?>
    <br>
    <div class="conteudo">
        <div class="container mt-4">
            <div class="row align-items-center">
                <div class="col-md-8 text-center">
                    <h1>Agendamentos online de vacinas</h1>
                    <h4>Agende suas vacinas comigo, sua plataforma de saúde digital!</h4>
                    <a href="./paginas/agendamentos "> <button class="btn btn-outline-success mt-3 me-3" type="button">Agendar</button></a>
                    <a href="./paginas/listarAgendamentos "> <button class="btn btn-outline-success mt-3 " type="button">Listar Agendas</button></a>
                </div>
                <div class="col-md-4">
                    <img src="./img/imagem4.png" alt="Imagem" class="img-fluid">
                </div>
            </div>
        </div>
    </div>

    <div class="descricao">
        <div class="row mt-4 justify-content-center">
            <div class="col-md-3 text-center">
                <i class="fa-solid fa-clock fs-2"></i>
                <p class="mt-2"><strong>Agende suas vacinas <br>
                        de forma rápida e segura. </strong></p>
            </div>
            <div class="col-md-3 text-center">
                <i class="fa-solid fa-calendar-days fs-2"></i>
                <p class="mt-2"><strong>Atendimento personalizado <br>
                        por profissionais qualificados.</strong></p>
            </div>
            <div class="col-md-3 text-center">
                <i class="fa-solid fa-shield fs-2"></i>
                <p class="mt-2"> <strong>Garantia de segurança qualidade <br>
                        em todos os procedimentos.</strong>
                </p>
            </div>
        </div>
    </div>
    <br>


    <div class="container mt-4">
        <div class="row">
            <div class="col-md-6">

                <h3>As campanhas de vacinação são pilares essenciais da saúde pública global. Elas desempenham um papel crucial na proteção contra doenças infecciosas,
                    salvando milhões de vidas todos os anos.
                    <br>
                    Através da vacinação, podemos prevenir uma série de doenças que antes eram devastadoras, como poliomielite, sarampo, rubéola, entre outras..
                </h3>
            </div>
            <div class="col-md-6">
                <img src="./img/imagem5.jpg" alt="Imagem Intuitiva" class="img-fluid">
            </div>
        </div>
    </div>
    </div>



    <!-- Footer -->
    <footer class="footer-custom">
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-4 text-center">
                    <h3>Contato</h3>
                    <p>hackathon2024@gmail.com</p>
                    <p>Telefone: (44) 99999-9999</p>
                    <p>Faculdade Unialfa - PR</p>
                </div>
                <div class="col-md-4 text-center links-center">
                    <h3>Links Úteis</h3>
                    <ul class="list-unstyled">
                        <li><a href="./paginas/home">Página Inicial</a></li>
                        <li><a href="./paginas/agendamentos">Faça um Agendamento</a></li>
                        <li><a href="./paginas/listarAgendamentos">Listar Agendamentos</a></li>
                    </ul>
                </div>
                <div class="col-md-4 text-center">
                    <h3>Siga-nos</h3>
                    <div class="social-icons">
                        <a href="https://www.facebook.com"><i class="fab fa-facebook-f"></i></a>
                        <a href="https://x.com/?lang=pt-br"><i class="fa-brands fa-x-twitter"></i></a>
                        <a href="https://www.instagram.com"><i class="fab fa-instagram"></i></a>
                        <a href="https://br.linkedin.com"><i class="fab fa-linkedin-in"></i></a>
                    </div>
                </div>
            </div>
            <div class="row mt-3">
                <div class="col text-center">
                    <p>&copy; 2024 Sua Empresa. Todos os direitos reservados.</p>
                </div>
            </div>
        </div>
    </footer>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>

</html>