INSERT INTO tasks (id, title, description, status, created_at)
VALUES
   (nextval('seq_tasks'), 'Implementar Login', 'Desenvolver a funcionalidade de login para o sistema.', 'PENDING', '2024-05-01 10:00:00'),
   (nextval('seq_tasks'), 'Criar Página de Contato', 'Desenvolver a página de contato com formulário.', 'DONE', '2024-05-02 11:30:00'),
   (nextval('seq_tasks'), 'Otimizar Banco de Dados', 'Melhorar a performance das consultas no banco de dados.', 'IN_PROGRESS', '2024-05-03 14:45:00'),
   (nextval('seq_tasks'), 'Configurar CI/CD', 'Configurar o pipeline de integração e entrega contínua.', 'PENDING', '2024-05-04 09:15:00'),
   (nextval('seq_tasks'), 'Escrever Documentação', 'Documentar as principais funcionalidades do sistema.', 'PENDING', '2024-05-05 16:20:00'),
   (nextval('seq_tasks'), 'Realizar Testes de Unidade', 'Escrever e executar testes de unidade para o módulo X.', 'IN_PROGRESS', '2024-05-06 13:10:00'),
   (nextval('seq_tasks'), 'Atualizar Dependências', 'Atualizar todas as dependências do projeto para as versões mais recentes.', 'DONE', '2024-05-07 08:50:00'),
   (nextval('seq_tasks'), 'Implementar Autenticação 2FA', 'Adicionar suporte para autenticação de dois fatores.', 'PENDING', '2024-05-08 17:30:00'),
   (nextval('seq_tasks'), 'Refatorar Código Antigo', 'Refatorar o código legado para seguir as novas diretrizes de codificação.', 'IN_PROGRESS', '2024-05-09 12:40:00'),
   (nextval('seq_tasks'), 'Monitorar Performance', 'Implementar monitoramento de performance em tempo real.', 'PENDING', '2024-05-10 15:55:00');
