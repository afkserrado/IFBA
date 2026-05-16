import { useParams } from 'react-router-dom';

import Q2 from '../../components/Questão 2';
import Q3_4 from '../../components/Questão 3_4';

const componentes = {
    '2': Q2,
    '3': Q3_4,
    '4': Q3_4
}

function Questoes() {
    const { id } = useParams();
    const Componente = componentes[id];
    return Componente ? <Componente /> : <h1>Questão ainda não disponível!</h1>;
}

export default Questoes;