1- O que é usestate?
O useState é um Hook do React (versão 16.8+) que permite adicionar estado local a componentes funcionais. Ele gerencia valores que mudam ao longo do tempo (como formulários, contadores ou visibilidade), forçando o componente a renderizar novamente quando o valor é atualizado. Retorna um array com o valor atual e uma função para alterá-lo. O useState recebe o valor inicial e retorna um par: o valor atual e uma função de atualização.

2- O que é UseEffect
O useEffect é um Hook do React usado para lidar com efeitos colaterais em componentes funcionais, como buscar dados de API, manipular o DOM diretamente, configurar subscrições ou timers. Ele executa código após a renderização, permitindo que a interface seja atualizada sem bloquear o navegador. O useEffect substitui métodos de ciclo de vida de componentes de classe, como componentDidMount, componentDidUpdate e componentWillUnmount.

3- Exemplo Usestate
import React, { useState } from 'react';

function Contador() {
  const [contador, setContador] = useState(0);

  return (
    <div>
      <p>Você clicou {contador} vezes</p>
      <button onClick={() => setContador(contador + 1)}>
        Incrementar
      </button>
    </div>
  );
}

4- Exemplo UseEffect
import React, { useState, useEffect } from 'react';

function Exemplo() {
  const [count, setCount] = useState(0);

  useEffect(() => {
    document.title = `Você clicou ${count} vezes`;
  });

  return (
    <div>
      <p>Você clicou {count} vezes</p>
      <button onClick={() => setCount(count + 1)}>
        Clique aqui
      </button>
    </div>
  );
}
