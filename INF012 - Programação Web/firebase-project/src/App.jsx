import { useState, useEffect } from 'react';
import { auth } from './services/firebaseConnection';
import { createUserWithEmailAndPassword, GoogleAuthProvider, onAuthStateChanged, signInWithEmailAndPassword, signInWithPopup, signOut } from 'firebase/auth'

export default function App() {

    // Cadastro e login
    const [email, setEmail] = useState("");
    const [senha, setSenha] = useState("");
    
    // Monitorar usuário
    const [user, setUser] = useState(false);
    const [logedUser, setLogedUser] = useState({});

    useEffect(() => {
        function checkLogin() {
            onAuthStateChanged(auth, user => {
                if(user) {
                    setUser(true);
                    setLogedUser({
                        uid: user.uid,
                        email: user.email
                    });
                }

                else {
                    setUser(false);
                    setLogedUser({});
                }
            })
        }

        checkLogin();
    }, []);

    async function novoUsuario() {
        try {
            const value = await createUserWithEmailAndPassword(auth, email, senha)
            console.log(value);
        }
        
        catch(error) {
            if(error.code === 'auth/weak-password') {
                    alert('Senha fraca.');
                }
            else if(error.code === 'auth/email-already-in-use') {
                alert('E-mail já em uso');
            }
        }
    }

    async function logout() {
        try {
            await signOut(auth);
            alert('Usuário desconectado'); 
        }
        catch (error) {
            console.log(error);
        }
    }

    async function login() {
        try {
            const value = await signInWithEmailAndPassword(auth, email, senha);
            console.log(value);
        }

        catch (error) {
            console.log(error);
            alert(error.message);
        }
    }

    async function loginGoogle() {
        try {
            var provider = new GoogleAuthProvider();
            provider.addScope('profile');
            provider.addScope('email');
            
            const value = await signInWithPopup(auth, provider);
            console.log(value.user.displayName, value.user.email);
        }

        catch (error) {
            console.log(error);
            alert(error.message);
        }
    }

    return(
        <div>
            <h1>FIREBASE + REACT</h1>

            {user && (
                <div>
                    <strong>Seja bem-vindo, </strong>
                    <span> {logedUser.email}!</span>
                </div>
            )}
            <br/>

            <label>Email: </label>
            <input type='email' value={email} onChange={(e) => setEmail(e.target.value)}/>
            <br/>
            <label>Senha: </label>
            <input type='password' value={senha} onChange={(e) => setSenha(e.target.value)}/>
            <br/>
            <br/>
            <button onClick={() => novoUsuario()}>Cadastrar</button>
            <button onClick={() => login()}>Login</button>
            <button onClick={() => logout()}>Logout</button>
            <button onClick={() => loginGoogle()}>Login com Google</button>

        </div>
    );
}