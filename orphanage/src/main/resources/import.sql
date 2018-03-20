INSERT INTO [dbo].[role] ([name]) VALUES ('SYSTEM');
INSERT INTO [dbo].[role] ([name]) VALUES ('ADMIN');
INSERT INTO [dbo].[role] ([name]) VALUES ('CARETAKER');
INSERT INTO [dbo].[role] ([name]) VALUES ('CURATOR');
INSERT INTO [dbo].[user_table] ([id],[password] ,[username]) VALUES (1 ,'$2a$10$gSAhZrxMllrbgj/kkK9UceBPpChGWJA7SYIb1Mqo.n5aNLq1/oRrC', 'SYSTEM');
INSERT INTO [dbo].[authority] ([user_id],[authority_name]) VALUES (1,'SYSTEM');