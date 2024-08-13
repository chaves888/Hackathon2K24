<!DOCTYPE html>
<html lang="pt-BR">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Listagem de Agendamento</title>
    <link rel="stylesheet" href="./cssPages/listarAgendamento.css">
<script src="./jsPages/listarAgendamentos.js"></script>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap" rel="stylesheet">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</head>

<body>

    <div class="container mt-4">
        <div class="card">
            <div class="card-body">
                <table class="table table-bordered table-hover table-striped">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Nome do idoso</th>
                            <th>Nome do agente</th>
                            <th>Nome da vacina</th>
                            <th>Agendamento</th>
                            <th>Opções</th>
                        </tr>
                    </thead>
                    <tbody>
                        <?php
                        $link = "http://localhost:3001/agendamento";
                        $response = file_get_contents($link);
                        $dados = json_decode($response);

                        if ($dados === null) {
                            die('Erro ao decodificar JSON.');
                        }

                        foreach ($dados->agendamentos as $agendamento) {
                        ?>
                            <tr>
                                <td><?php echo $agendamento->id; ?></td>
                                <td><?php echo $agendamento->nome_idoso; ?></td>
                                <td><?php echo $agendamento->nome_agente; ?></td>
                                <td><?php echo $agendamento->nome_vacina; ?></td>
                                <td><?php echo $agendamento->agendamento; ?></td>
                                <td>

                                    <button type="button" class="btn btn-success" onclick="abrirModalEditar(<?php echo $agendamento->id; ?>)">
                                        <i class="fas fa-edit"></i> Editar 
                                    </button>


                                    <button type="button" class="btn btn-danger" onclick="deleteAgendamento(<?php echo $agendamento->id; ?>)">
                                        <i class="fas fa-trash"></i> Cancelar
                                    </button>
                                </td>
                                </a>
                                </td>
                                <div class="modal fade" id="modalEditar" tabindex="-1" role="dialog" aria-labelledby="modalEditarLabel" aria-hidden="true">
                                    <div class="modal-dialog" role="document">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title" id="modalEditarLabel">Editar Agendamento</h5>
                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                    <span aria-hidden="true">&times;</span>
                                                </button>
                                            </div>
                                            <div class="modal-body">
                                                <form id="formEditarAgendamento">
                                                    <!-- Campos de edição -->
                                                    <div class="form-group">
                                                        <label for="nome_idoso">Nome do Idoso</label>
                                                        <input type="text" class="form-control" id="nome_idoso" name="nome_idoso" required>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="nome_vacina">Nome da Vacina</label>
                                                        <input type="text" class="form-control" id="nome_vacina" name="nome_vacina" required>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="agendamento">agendamento</label>
                                                        <input type="date" class="form-control" id="agendamento" name="agendamento" required>
                                                    </div>
                                                    <!-- Adicione mais campos conforme necessário -->
                                                    <button type="submit" class="btn btn-success">Salvar</button>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </tr>
                        <?php
                        }
                        ?>
                    </tbody>
                </table>
                <div class="text-center mt-3">
                    <a href="./paginas/home" class="btn btn-primary">
                        <i class="fas fa-home"></i> Voltar para a Página Home
                    </a>
                </div>
            </div>
        </div>
</body>


<script>
    function deleteAgendamento(id) {
        if (confirm('Tem certeza que deseja cancelar este agendamento?')) {
            fetch(`http://localhost:3001/agendamentos/${id}`, {
                    method: 'DELETE',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                })
                .then(response => {
                    if (response.ok) {
                        alert('Agendamento cancelado com sucesso.');
                        location.reload();
                    } else {
                        alert('Erro ao cancelar o agendamento.');
                    }
                })
                .catch(error => {
                    console.error('Erro:', error);
                    alert('Erro ao cancelar o agendamento.');
                });
        }
    }

    function abrirModalEditar(id) {

        fetch(`http://localhost:3001/agendamentos/${id}`)
            .then(response => response.json())
            .then(data => {
                document.getElementById('nome_idoso').value = data.agendamento.nome_idoso;
                document.getElementById('nome_vacina').value = data.agendamento.nome_vacina;
                document.getElementById('agendamento').value = data.agendamento.agendamento;
                $('#modalEditar').modal('show');
            })
            .catch(error => {
                console.error('Erro ao carregar os dados do agendamento.', error);
                alert('Erro ao carregar os dados do agendamento.');
            });


        document.getElementById('formEditarAgendamento').onsubmit = function(event) {
            event.preventDefault();
            const formData = new FormData(this);
            fetch(`http://localhost:3001/agendamento/${id}`, {
                    method: 'PUT',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(Object.fromEntries(formData))
                })
                .then(response => {
                    if (response.ok) {
                        alert('Agendamento atualizado com sucesso.');
                        location.reload();
                    } else {
                        alert('Erro ao atualizar o agendamento.');
                    }
                })
                .catch(error => {
                    console.error('Erro:', error);
                    alert('Erro ao atualizar o agendamento.');
                });
        };
    }



</html>