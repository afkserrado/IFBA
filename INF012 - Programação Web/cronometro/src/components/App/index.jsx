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

        this.intervalo = null; // Identifica um timer definido por 'setInterval()'
    }

    vai() {
                
        // Cronometro parado
        // Cronometro é iniciado
        if(this.intervalo === null) {
            this.setState({ botao: 'Pausar' }); // Muda o texto do botão
            
            this.intervalo = setInterval(() => {
                this.setState({ tempo: this.state.tempo + 0.1 });
            }, 100);
        }

        // Cronometro contando
        // Cronometro é pausado
        else {
            this.setState({ botao: 'Vai' }); // Muda o texto do botão
            this.reset();
        }
    }

    limpar() {
        this.setState({ tempo: 0 , botao: 'Vai'})
        this.reset();       
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