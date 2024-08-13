/**
 * @param { import("knex").Knex } knex
 * @returns { Promise<void> }
 */
exports.up = function(knex) {
    return knex.schema.createTable('agente',
        (table) => {
            table.increments('id')
            table.text('nome').notNullable();
           }
    ).then(() => {
        console.log('Tabela de agente criada!!')
    })
};
  


/**
 * @param { import("knex").Knex } knex
 * @returns { Promise<void> }
 */
exports.down = function(knex) {
    return knex.schema.dropTable('agente')
    .then(() => {
        console.log('Deletado tabela de agente')
    })
  
};
