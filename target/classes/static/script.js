document.addEventListener('DOMContentLoaded', () => {
    const tabButtons = document.querySelectorAll('.tab-button');
    const formContents = document.querySelectorAll('.form-content > div');

    tabButtons.forEach(button => {
        button.addEventListener('click', () => {
            tabButtons.forEach(btn => btn.classList.remove('active'));
            formContents.forEach(content => content.classList.remove('active'));

            button.classList.add('active');
            const targetId = button.dataset.tab;
            const targetForm = document.getElementById(`${targetId}-form`);
            if (targetForm) {
                targetForm.classList.add('active');
            }
        });
    });

    // Gestion du formulaire de connexion
    const loginForm = document.querySelector('#login-form form');
    loginForm.addEventListener('submit', function(e) {
        e.preventDefault();
        
        const loginData = {
            login: this.querySelector('input[type="text"]').value,
            password: this.querySelector('input[type="password"]').value
        };

        loginUser(loginData);
    });

    // Gestion du formulaire d'inscription
    const registerForm = document.querySelector('#register-form form');
    registerForm.addEventListener('submit', function(e) {
        e.preventDefault();
        
        const inputs = this.querySelectorAll('input');
        const registerData = {
            nom: inputs[0].value,
            prenom: inputs[1].value, // Ajout du prénom
            filiere: inputs[3].value, // Valeur par défaut
            dateNaiss: new Date(), // Date actuelle par défaut
            login: inputs[1].value, // prénom comme login
            password: inputs[5].value
        };

        registerUser(registerData);
    });
});

// Fonction pour la connexion
async function loginUser(loginData) {
    try {
        const response = await fetch('http://localhost:8080/api/etudiant/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(loginData)
        });

        const result = await response.text();
        alert(result);
        
        if (result.includes("Bienvenue")) {
            // Redirection vers une page d'accueil après connexion réussie
            window.location.href = '/accueil';
        }
    } catch (error) {
        console.error('Erreur:', error);
        alert('Erreur de connexion au serveur');
    }
}

// Fonction pour l'inscription
async function registerUser(userData) {
    try {
        const response = await fetch('http://localhost:8080/api/etudiant/register', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(userData)
        });

        const result = await response.text();
        alert(result);
        
        if (result.includes("Inscription réussie")) {
            // Basculer vers l'onglet connexion après inscription
            document.querySelector('[data-tab="login"]').click();
        }
    } catch (error) {
        console.error('Erreur:', error);
        alert('Erreur lors de l\'inscription');
    }
}