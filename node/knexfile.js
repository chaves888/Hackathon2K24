const path = require('path')

module.exports = {
  development: {
    client: 'mysql',
    connection: {
      host: 'localhost', 
            user: 'root', 
            password: '', 
            database: 'vacina'
          },
  
   

    migrations: {
      directory: path.resolve(
        __dirname,
        'src',
        'database',
        'knex',
        'migrations'
      )
    },
    useNullAsDefault: true
  }
}
