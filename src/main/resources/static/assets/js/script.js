// Basic Login Form Script
class BasicLoginForm {
    constructor() {
        this.form = document.getElementById('loginForm');
        this.usernameInput = document.getElementById('username');
        this.passwordInput = document.getElementById('password');
        this.passwordToggle = document.getElementById('passwordToggle');

        this.init();
    }

    init() {
        // Initialize shared utilities
        FormUtils.addSharedAnimations();
        FormUtils.setupFloatingLabels(this.form);
        FormUtils.setupPasswordToggle(this.passwordInput, this.passwordToggle);

        // Add event listeners
        this.form.addEventListener('submit', this.handleSubmit.bind(this));
        this.usernameInput.addEventListener('input', () => this.validateField('username'));
        this.passwordInput.addEventListener('input', () => this.validateField('password'));

        // Add entrance animation
        FormUtils.addEntranceAnimation(this.form.closest('.login-card'), 100);
    }

    validateField(fieldName) {
        const input = document.getElementById(fieldName);
        const value = input.value.trim();
        let validation;

        FormUtils.clearError(fieldName);

        if (fieldName === 'username') {
            validation = FormUtils.validateUsername(value);
        } else if (fieldName === 'password') {
            validation = FormUtils.validatePassword(value);
        }

        // Cek jika validasi gagal atau input kosong
        if (!validation.isValid) {
            FormUtils.showError(fieldName, validation.message);
            return false;
        }

        // Jika validasi sukses
        FormUtils.showSuccess(fieldName);
        return true;
    }

    handleSubmit(e) {
        e.preventDefault();

        const usernameValid = this.validateField('username');
        const passwordValid = this.validateField('password');

        if (!usernameValid || !passwordValid) {
            FormUtils.showNotification('Please fix the errors below', 'error', this.form);
            return;
        }

        const submitBtn = this.form.querySelector('.login-btn');
        submitBtn.classList.add('loading');

        // Jika validasi sukses, kirim form ke server
        this.form.submit();
    }
}

document.addEventListener('DOMContentLoaded', () => {
    new BasicLoginForm();
});