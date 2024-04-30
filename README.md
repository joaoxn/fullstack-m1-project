# FULLSTACK EDUCATION
## _Projeto: Back-End_: labPCP
- - - 
Integrada à empresa **LAB365 Developer**, o Software "labPCP" foi desenvolvido.  
- labPCP é um projeto de API Rest completa para gestão de cursos, turmas, conteúdos e docentes, que será futuramente integrada à soluções web de gestão em escolas e creches da rede pública.
<br/><br/>
### Tecnologias
Para desenvolver a API Rest, foi utilizado a linguagem de programação **Java** juntamente ao _framework_ **Spring** em um projeto **Maven** com PostgreSQL sendo a escolha para banco de dado.

As dependências Spring utilizadas são:
- Spring Data JPA
- OAuth2 Resource Server
- Spring Security
- Spring Web
- PostgreSQL Driver
- Lombok

### Configuração
#### Banco de dados
Para executar o projeto, você precisa primeiro criar um banco de dados PostgreSQL em sua máquina. Para criar usando o pgAdmin 4, siga o passo-a-passo:
1. Abra o aplicativo "pgAdmin 4";


2. Registre um novo servidor em sua máquina (Clique com o botão direito em um grupo de servidores, vá em 'Register' e clique em Server);


3. Configure o servidor da seguinte forma: <br/><br/>
Name (Nome): postgres;  
Host name/address (Nome ou endereço do host): localhost;  
Port: 5432;  
Username (Usuário): postgres;  
Password (Senha): postgres;


4. Salve para criar o banco de dados;


5. Pronto, você configurou o banco de dados!

#### Projeto
Após configurar o banco de dados, abra o projeto, localize o arquivo [EducacionalApplication.java](src/main/java/com/fullstack/educacional/EducacionalApplication.java) e execute-o.  

Para interagir com o projeto, utilize uma aplicação que permite testar APIs web, como o Postman, para acessar os endpoints. A baseUrl do projeto é 'localhost:8081'. Portanto, para acessar os endpoints do projeto, utilize da seguinte forma:  
localhost:8081/{endpoint}

Relação das entidades:
()

### Como utilizar
O projeto possui vários endpoints, mas a maioria deles não são acessíveis ao público. O único endpoint disponível publicamente é:  
- #### localhost:8081/login
Para acessar os outros endpoints, você precisará entrar em uma conta existente através do endpoint **GET localhost:8081/login**.  
Aviso: Algumas contas têm acesso a menos endpoints que outras, variando com o papel de cada um. Para acesso total, entre em uma conta de administrador.  

#### Banco auto-populado
O projeto tem, por padrão, entidades criadas automaticamente. Para verificar todas, acesse o arquivo [**data.sql**](src/main/resources/data.sql) presente no pacote resources.

- **Usuário ADMIN padrão**
- - login: admin
- - senha: 123

AVISO: O programa atual reseta o Banco de Dados toda a vez que for reinicializado a aplicação. Isso foi feito pela forma como o projeto está sendo utilizado no momento. Caso fosse feito diferente, novas entidades duplicariam a cada reinicialização, por causa de [**data.sql**](src/main/resources/data.sql). Se for importante e desejável que as entidades salvem ao invés de serem deletadas a cada reinicialização da aplicação,   

### Endpoints
Os endpoints só são acessíveis a certos usuários.
Aqui está a lógica de permissão:
ADM (Entidade Docente): que pode acessar tudo.
PEDAGOGICO (Entidade Docente): que pode acessar tudo sobre turma, curso e professor, aluno, menos deletar dados.
RECRUITER (Entidade Docente): que pode acessar tudo sobre professor, menos deletar dados.
PROFESSOR (Entidade Docente): que pode acessar tudo sobre notas, menos deletar dados.
ALUNO (Entidade Aluno): que apenas acessa as suas próprias notas e a pontuação total pessoal, menos deletar dados.

- Login de Usuário `POST localhost:8081/login` - Público
- `PUT localhost:8081/usuarios/senha` - Autenticado (Qualquer usuário logado) - Altera senha do usuário logado. Requer uma String no body


- `GET localhost:8081/alunos/{id}`
- `GET localhost:8081/alunos`
- `POST localhost:8081/alunos`
- `PUT localhost:8081/alunos/{id}`
- `DELETE localhost:8081/alunos/{id}`
- `GET localhost:8081/alunos/{id}/notas`
- `GET localhost:8081/alunos/{id}/pontuacao`


- `GET localhost:8081/cursos/{id}`
- `GET localhost:8081/cursos`
- `POST localhost:8081/cursos`
- `PUT localhost:8081/cursos/{id}`
- `DELETE localhost:8081/cursos/{id}`
- `GET localhost:8081/cursos/{id}/materias`


- `GET localhost:8081/docentes/{id}`
- `GET localhost:8081/docentes`
- `POST localhost:8081/docentes`
- `PUT localhost:8081/docentes/{id}`
- `DELETE localhost:8081/docentes/{id}`


- `GET localhost:8081/materias/{id}`
- `GET localhost:8081/materias`
- `POST localhost:8081/materias`
- `PUT localhost:8081/materias/{id}`
- `DELETE localhost:8081/materias/{id}`


- `GET localhost:8081/notas/{id}`
- `GET localhost:8081/notas`
- `POST localhost:8081/notas`
- `PUT localhost:8081/notas/{id}`
- `DELETE localhost:8081/notas/{id}`


- `GET localhost:8081/turmas/{id}`
- `GET localhost:8081/turmas`
- `POST localhost:8081/turmas`
- `PUT localhost:8081/turmas/{id}`
- `DELETE localhost:8081/turmas/{id}`


- `GET localhost:8081/usuarios` - ADMIN
- `GET localhost:8081/usuarios/{id}` - ADMIN
- `DELETE localhost:8081/usuarios/{id}` - ADMIN
- `POST localhost:8081/papel` - ADMIN
- `POST localhost:8081/cadastro` - ADMIN
