# 📚 BibliotecaMaven

Sistema de gerenciamento de bibliotecas desenvolvido em **Java** com **JPA + Hibernate** e **MySQL**, permitindo **cadastro, login e controle de empréstimos de livros**.  
Projeto acadêmico criado para praticar os fundamentos de **Programação Orientada a Objetos (POO)**, **persistência de dados** e **controle de usuários** com diferentes níveis de permissão.

---

## 🚀 Funcionalidades Principais

### 👥 Sistema de Usuários
- Cadastro e login de dois tipos de usuário:
  - **Usuário comum:** pode consultar o catálogo e realizar empréstimos.
  - **Gerente:** possui acesso administrativo completo (criação e exclusão de bibliotecas, livros e etiquetas).

### 🏛️ Gerenciamento de Bibliotecas
- O **gerente** pode:
  - Criar novas bibliotecas (ex: *Amazon*, *Leitura*).
  - Adicionar ou remover livros de cada biblioteca.
  - Adicionar e remover **etiquetas** nos livros.
  - Excluir bibliotecas completas.
  - Cancelar empréstimos ativos.

### 📖 Empréstimos e Catálogo
- O **usuário comum** pode:
  - Consultar o catálogo de livros disponíveis.
  - Realizar empréstimos (prazo padrão de **2 semanas**).
  - Receber notificação de **multa de R$30,00** caso o prazo seja excedido.

---

## 🛠️ Tecnologias Utilizadas

- **Java 17+**
- **Maven** (gerenciamento de dependências)
- **MySQL Workbench** (banco de dados)
- **JPA + Hibernate** (persistência de dados)
- **Paradigma de Programação Orientada a Objetos**

---

## ⚙️ Configuração do Banco de Dados

O projeto utiliza **MySQL** e **JPA (Hibernate)** para gerenciar a persistência de dados.  
As configurações estão no arquivo `persistence.xml`.

### Exemplo de configuração:
```xml
<persistence-unit name="biblioteca" transaction-type="RESOURCE_LOCAL">
    <class>com.example.servlet.User</class>
    <properties>
        <property name="javax.persistence.jdbc.driver" value="com.mysql.cj.jdbc.Driver" />
        <property name="javax.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/testebd" />
        <property name="javax.persistence.jdbc.user" value="usuario" />
        <property name="javax.persistence.jdbc.password" value="senha" />
        <property name="hibernate.dialect" value="org.hibernate.dialect.MySQL5Dialect" />
        <property name="hibernate.hbm2ddl.auto" value="update" />
    </properties>
</persistence-unit>

