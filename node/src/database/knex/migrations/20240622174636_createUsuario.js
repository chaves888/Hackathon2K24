/**
 * @param { import("knex").Knex } knex
 * @returns { Promise<void> }
 */
exports.up = function(knex) {
    return knex.schema.createTable('usuario',
        (table) => {
            table.increments('id')
            table.text('nome').notNullable;
            table.text('email').notNullable;
            table.text('login').notNullable();
            table.text('senha').notNullable();
            table.text('ativo').notNullable();

           }
    ).then(() => {
        console.log('Tabela de usuário criada!!')
    })

};

/**
 * @param { import("knex").Knex } knex
 * @returns { Promise<void> }
 */
exports.down = function(knex) {
    return knex.schema.dropTable('usuario')
    .then(() => {
        console.log('Deletado tabela de usuário')
    })
  
};
