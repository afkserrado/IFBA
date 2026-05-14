import { Component } from 'react';

import '../../App.css'

import cronometro from '../../assets/cronometro.png'

class App extends Component {

    constructor(props) {
        super(props);
        this.state = {
            tempo: 0.0,
            botao: 'Vai'
        };

        // Identifica um timer definido por 'setInterval()'
        // Não precisa fazer parte do 'state' porque não é
        // utilizado para renderizar a interface
        this.intervalo = null; 
    }

    vai() {
                
        // Cronometro não foi iniciado nenhuma vez
        // Inicia o cronometro
        if(this.intervalo === null) {
            this.setState({ botao: 'Pausar' }); // Muda o texto do botão
            
            // Cria um timer
            this.intervalo = setInterval(() => {
                this.setState({ tempo: this.state.tempo + 0.1 });
            }, 100);
        }

        // Cronometro contando
        // Cronometro é pausado
        else {
            this.setState({ botao: 'Vai' }); // Muda o texto do botão
            this.reset(); // Reseta o timer
        }
    }

    limpar() {
        this.setState({ tempo: 0 , botao: 'Vai'})
        this.reset(); // Reseta o timer   
    }

    reset() {
        clearInterval(this.intervalo); // Interrompe o timer correspondente
        this.intervalo = null; 
    }

    render() {
        return(
            <div className="conteiner">
                <img src={cronometro} className="img" />
                <a className="timer">{this.state.tempo.toFixed(1)}</a>
                <div className="areaTexto">
                    <button className="botao" onClick={this.vai.bind(this)}>{this.state.botao}</button>
                    <button className="botao" onClick={this.limpar.bind(this)}>Limpar</button>
                </div>
            </div>
        );
    }
} export default App;