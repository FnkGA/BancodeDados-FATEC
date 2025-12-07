# Cadastro de Monumentos - Interface Web

Interface web simples e funcional para cadastro de monumentos, baseada no protótipo fornecido.

## Estrutura do Projeto

```
├── index.html          # Interface principal
├── styles.css          # Estilos da interface
├── script.js           # Lógica JavaScript
└── CRUD/
    └── src/
        ├── MonumentoServlet.java  # API REST para comunicação com o backend
        ├── MonumentoDAO.java      # DAO existente
        └── ConnectionFactory.java # Factory de conexão
```

## Requisitos

- Java 8 ou superior
- Servidor de aplicação (Tomcat, Jetty, etc.)
- PostgreSQL (banco de dados configurado)
- Servlet API (geralmente incluída no servidor de aplicação)

## Configuração

### 1. Backend (Java)

1. O servlet `MonumentoServlet.java` não requer bibliotecas externas - usa parsing JSON básico
2. Configure o servlet no `web.xml` (se necessário) ou use a anotação `@WebServlet`
3. Deploy no servidor de aplicação (Tomcat, etc.)
4. Certifique-se de que o banco de dados PostgreSQL está rodando e acessível

### 2. Frontend

1. Abra o arquivo `index.html` em um navegador web
2. Ou configure um servidor web simples (ex: Python `python -m http.server 8000`)

### 3. Configuração da API

O JavaScript está configurado para fazer requisições para:
- URL: `http://localhost:8080/api/monumentos`
- Método: POST (para cadastro)

Se seu servidor estiver em outra porta, edite o arquivo `script.js` na linha:
```javascript
const response = await fetch('http://localhost:8080/api/monumentos', {
```

## Campos do Formulário

- **Nome***: Nome do monumento
- **Endereço***: Cidade, estado ou região
- **Artista***: Nome do artista
- **Descrição***: Descrição do monumento
- **Decreto**: Número do decreto (opcional)
- **ID da Localidade***: ID da localidade (ex: LOC-001)
- **Imagem**: Upload de imagem (opcional, funcionalidade básica)

## Funcionalidades

- ✅ Formulário responsivo com design moderno
- ✅ Validação de campos obrigatórios
- ✅ Integração com API REST
- ✅ Mensagens de sucesso/erro
- ✅ Upload de arquivos (interface preparada)
- ✅ Design baseado no protótipo fornecido

## Notas

- O upload de imagens está preparado na interface, mas o backend precisa ser implementado para salvar os arquivos
- A API inclui CORS headers para permitir requisições do frontend
- O design segue o tema escuro com acentos laranja conforme o protótipo

