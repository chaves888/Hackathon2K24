/**
 * @param { import("knex").Knex } knex
 * @returns { Promise<void> }
 */
exports.up = function(knex) {
    return knex.schema.createTable('vacina',
        (table) => {
            table.increments('id')
            table.text('nome').notNullable();
           }
    ).then(() => {
        console.log('Tabela de vacina criada!!')
    })
  
};

/**
 * @param { import("knex").Knex } knex
 * @returns { Promise<void> }
 */
exports.down = function(knex) {
    return knex.schema.dropTable('vacina')
    .then(() => {
        console.log('Deletado tabela de vacina')
    })
};
