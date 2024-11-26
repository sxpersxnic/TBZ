const users = [];

function handleLogin(event) {
    event.preventDefault();
    const email = document.querySelector('[name="email"]').value;
    const password = document.querySelector('[name="password"]').value;
    const user = users.find((u) => u.email === email && u.password === password);
    if (user) {
        alert(`Welcome, ${user.username}!`);
    } else {
        alert('Invalid credentials. Please try again.');
    }
}

function isValidUsername(username) {
    const regex = /^[a-zA-Z0-9_-]+$/;
    return regex.test(username);
}

function isValidEmail(email) {
    const regex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    return regex.test(email);
}

function handleRegistration(event) {
    event.preventDefault();
    const username = document.querySelector('[name="username"]').value;
    const email = document.querySelector('[name="email"]').value;
    const password = document.querySelector('[name="password"]').value;
    const confirmPassword = document.querySelector('[name="confirmPassword"]').value;
    
    if (!isValidUsername(username)) {
        alert('Invalid username. Use alphanumeric characters, hyphens, or underscores.');
        return;
    }
    if (!isValidEmail(email)) {
        alert('Invalid email address.');
        return;
    }
    if (password !== confirmPassword) {
        alert('Passwords do not match. Please try again.');
        return;
    }
    
    const existingUser = users.find((user) => user.username === username);
    if (existingUser) {
        alert('Username already taken. Please choose another.');
        return;
    }

    users.push({ username, email, password });
    alert('Registration successful! You can now log in.');
}

document.querySelector('#register-form').addEventListener('submit', handleRegistration);
document.querySelector('#login-form').addEventListener('submit', handleLogin);