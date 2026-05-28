// Define o componente IMC

/*
Importa a classe base Component do React para criar componentes com estado
Um componente é um pedaço reutilizável da interface (UI). Ele:
    - Recebe dados (props);
    - Pode ter estado interno (state);
    - Retorna o que deve aparecer na tela (JSX).

Em outras palavras, um componente é como uma função que recebe dados, processa e gera uma saída na tela (HTML).
*/
import { Component } from "react" 

// Cria um componente de classe chamado IMC estendendo Component, herdando dele recursos como 'state' e 'render()'
// 'state' é o estado interno do componente
class IMC extends Component {

    // Construtor da classe
    constructor(props) {
        super(props);
        
        // 'state' é um objeto JS
        this.state={
            peso:0,
            altura:0,
            imc:""
        };
    }

    // Método para calcular o IMC
    calcularIMC() {
        let peso = parseFloat(this.state.peso);
        let altura = parseFloat(this.state.altura);
        let valor = peso / (altura ** 2);
        
        let imc = "SOBREPESO";
        if(valor >= 30) {
            imc = "OBESIDADE";
        }
        else if(valor <= 25) {
            imc = "NORMAL";
        }

        // Para atualizar o estado interno de um componente, deve-se utilizar o método 'setState()'
        // 'setState()' recebe um objeto (com as alterações) ou uma função que retorna um objeto
        // Além de alterar o estado interno do objeto, 'setState()' também força o React a re-renderizar
        this.setState({ imc });
    }

    // Método que renderiza o conteúdo que deve aparecer na tela
    // Sempre que o 'state' muda, o React invoca 'render()' de novo
    render() {
        return (
            <div> {/* Container */}
                <form> {/* Estrutura HTML */}
                    <label>Peso </label>
                    <input type="text" value={this.state.peso}
                    onChange={(e)=>this.setState({ peso: e.target.value })} name="peso" /> <br /> <br />

                    <label>Altura </label>
                    <input type="text" value={this.state.altura} onChange={(e)=>this.setState({ altura: e.target.value })} name="altura" /> <br /> <br />
                    
                    <input type="button" onClick={()=>this.calcularIMC()} value="CALCULAR"></input>
                </form>
                <h1>{this.state.imc}</h1>
            </div>
        )
    }
} export default IMC;