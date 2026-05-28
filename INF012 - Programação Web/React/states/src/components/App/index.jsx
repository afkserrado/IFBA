import { Component } from 'react';

class App extends Component {

    constructor(props) {
        super(props);
        this.state = { 
            contador: 0
        };

        // Bind
        this.aumentar = this.aumentar.bind(this);
        this.diminuir = this.diminuir.bind(this);
    }

    aumentar() {
        this.setState({ contador: this.state.contador + 1 });
    }

    diminuir() {
        this.setState({ contador: this.state.contador - 1 });
    }

    zerar() {
        this.setState({ contador: 0 });
    }

    render() {
        return(
            <div>
                <h1>Contador</h1>
                <h1>{this.state.contador}</h1>
                <h2>
                    <button onClick = {this.diminuir}>-</button>
                    <button onClick = {this.aumentar}>+</button>
                    <button onClick = {this.zerar.bind(this)}>Zerar</button>
                </h2>
            </div>
        );
    }
} export default App;