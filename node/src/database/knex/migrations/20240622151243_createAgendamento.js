/**
 * @param { import("knex").Knex } knex
 * @returns { Promise<void> }
 */
exports.up = function(knex) {
    return knex.schema.createTable('agendamento',
        (table) => {
            table.increments('id')
            table.date('agendamento').notNullable();
            table.text('nome_idoso').notNullable();
            table.text('nome_agente').notNullable();
            table.text('nome_vacina').notNullable();
            table.text('status').notNullable();
           }
    ).then(() => {
        console.log('Tabela de agendamento criada!!')
    })
};
  

/**
 * @param { import("knex").Knex } knex
 * @returns { Promise<void> }
 */
exports.down = function(knex) {
    return knex.schema.dropTable('agendamento')
    .then(() => {
        console.log('Deletado tabela de agendamento')
    })
  
};
