# Padrão Proxy - Loja de Bicicletas

Este exemplo demonstra o padrão Proxy em uma loja de bicicletas.

A loja acessa o estoque através da interface `EstoqueBicicletas`.
A classe `EstoqueBicicletasProxy` controla o acesso ao objeto real `EstoqueRealBicicletas`.

## Pasta principal

`src/main/java/padroesestruturais/proxy/`

## Pasta de testes

`src/test/java/padroesestruturais/proxy/`

## Classes principais

- `EstoqueBicicletas`: interface comum entre o proxy e o objeto real.
- `EstoqueRealBicicletas`: objeto real, responsável pelo controle do estoque.
- `EstoqueBicicletasProxy`: proxy que controla acesso, usa cache e carrega o estoque real sob demanda.
- `LojaBicicletas`: classe cliente que usa a interface do estoque.
- `UsuarioLoja`: representa o usuário que acessa o sistema.
- `PerfilUsuario`: define CLIENTE, VENDEDOR e GERENTE.

## Ideia do padrão

O Proxy fica entre a loja e o estoque real.
Ele pode controlar permissões, evitar acesso direto ao objeto real e guardar consultas em cache.
