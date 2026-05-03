import { Component } from 'react';

class Tarefa extends Component {
    
    constructor(props) {
        super(props);
        this.state = {
            texto: "Digite sua tarefa",
            lista: ["lavar os pratos", "arrumar a cama"]
        };
    }

    addTarefa() {
        this.setState({ 
            lista: [this.state.texto, ...this.state.lista],
            texto: "" 
        })
    }

    render() {
        return(
            <div>
                <h2>Com class components</h2>
                <ul>{this.state.lista.map((item, index) => 
                    <li key={index}>{item}</li>)}
                </ul>
                
                <input 
                    type="text" 
                    value={this.state.texto} 
                    onChange={(e) => 
                        this.setState({ texto: e.target.value })} 
                />
                
                <button onClick={this.addTarefa.bind(this)}>Incluir</button>
            </div>
        );
    }

} export default Tarefa;