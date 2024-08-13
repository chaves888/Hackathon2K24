/**
 * @param { import("knex").Knex } knex
 * @returns { Promise<void> }
 */
exports.up = function(knex) {
    return knex.schema.createTable('tabela_idoso',
        (table) => {
            table.increments('id')
            table.text('nome').notNullable();
            table.text('observacao').notNullable();
           }
    ).then(() => {
        console.log('Tabela de idoso criada!!')
    })

};

/**
 * @param { import("knex").Knex } knex
 * @returns { Promise<void> }
 */
exports.down = function(knex) {
    return knex.schema.dropTable('tabela_idoso')
    .then(() => {
        console.log('Deletado tabela de vacina')
    })
  
};
