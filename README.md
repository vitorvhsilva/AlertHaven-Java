# AlertHaven-Java

# 🌪️ Sistema de Localização de Abrigos para Emergências

Este projeto foi desenvolvido com o objetivo de auxiliar pessoas em situações de emergência, como **terremotos**, **tsunamis** e **furacões**, a localizarem rapidamente abrigos disponíveis nas proximidades. Através da integração com a **API ViaCEP**, o sistema utiliza o CEP informado para preencher automaticamente os dados de endereço dos abrigos.

---

## ⚙️ Funcionalidades

O sistema oferece operações REST para o gerenciamento de **usuários** e **abrigos**.

### 🧑‍🤝‍🧑 Usuários
- 📥 Cadastro de usuários
- 📃 Listagem de todos os usuários
- 🔍 Consulta de usuário por ID
- 📝 Atualização dos dados de usuário
- ❌ Remoção de usuário

### 🏠 Abrigos
- 📥 Cadastro de abrigos
- 📃 Listagem de todos os abrigos
- 🔍 Consulta de abrigo por ID
- 📝 Atualização dos dados do abrigo
- ❌ Remoção de abrigo

### 🔗 Integração com ViaCEP
- No cadastro de abrigos, o sistema utiliza a **API do ViaCEP** via **OpenFeign** para buscar automaticamente os dados do endereço com base no CEP informado.

---

## 🛠️ Tecnologias e Dependências

- **Linguagem**: Java 17+
- **Framework**: Spring Boot
- **Banco de Dados**: Oracle
- **ORM**: Spring Data JPA
- **Documentação da API**: Swagger (OpenAPI)
- **Integração com API externa**: Spring Cloud OpenFeign
- **Outras dependências**:
  - Spring Web
  - Spring Validation
  - Lombok
  - DevTools
  - ModelMapper

---

## 🧪 Demonstração da API

Abaixo estão imagens exemplificando o funcionamento do sistema:

### 🏠 Abrigos

### 📥 Cadastrar Abrigo
![image](https://github.com/user-attachments/assets/991547f0-833f-45f8-a5fc-87f4c13f2a8c)


### 📃 Listar Todos os Abrigos
![image](https://github.com/user-attachments/assets/8e7ea7a6-3cf7-4835-9e41-7980fed37ed9)


### 🔍 Buscar Abrigo por ID
![image](https://github.com/user-attachments/assets/5e7323f9-4ee5-4d9d-af53-a11ab531e432)


### 📝 Atualizar Abrigo
![image](https://github.com/user-attachments/assets/c560d505-a9f8-464d-adf7-27864d3a1fad)


### ❌ Deletar Abrigo
![image](https://github.com/user-attachments/assets/745f88dd-3084-4477-97aa-cffefd7418c6)
**OBS: AO RETORNAR NO CONTENT SIGNIFICA QUE A EXECUÇÃO FOI FEITA COM SUCESSO**

### 👤 Usuários

### 📥 Cadastrar Usuário
![image](https://github.com/user-attachments/assets/5a067fe1-5826-4097-a1e8-fd54e4ef76f1)


### 📃 Listar Todos os Usuários
![image](https://github.com/user-attachments/assets/a53740d6-c8dc-4d4e-b11f-62189922c50a)


### 🔍 Buscar Usuário por ID
![image](https://github.com/user-attachments/assets/c13bc15c-35e0-4d50-b27a-f066c176c88a)


### 📝 Atualizar Usuário
![image](https://github.com/user-attachments/assets/d2b3f302-9b2c-43b4-86c6-ec5d5b575e49)


### ❌ Deletar Usuário
![image](https://github.com/user-attachments/assets/a9084b51-43f0-4ce1-b9b6-a04bd2e38fd8)
**OBS: AO RETORNAR NO CONTENT SIGNIFICA QUE A EXECUÇÃO FOI FEITA COM SUCESSO**

---

## 🔧 Como Executar o Projeto Localmente

**Clone o repositório**
git clone https://github.com/vitorvhsilva/AlertHaven-Java.git
