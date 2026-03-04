import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user.model';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {
  registrationForm: FormGroup;
  errorMessage: string = '';
  successMessage: string = '';

  constructor(private fb: FormBuilder,
              private authService: AuthService,
              private router: Router) { }

  ngOnInit(): void {
    this.registrationForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(8)]],
      confirmPassword: ['', Validators.required],
      username: ['', Validators.required],
      mobileNumber: ['', [Validators.required, Validators.pattern('^[0-9]{10}$')]],
      role: ['', Validators.required]
    }, { validator: this.passwordsMatchValidator });
  }

  passwordsMatchValidator(form: FormGroup): { [key: string]: boolean } | null {
    const password = form.get('password');
    const confirmPassword = form.get('confirmPassword');
    
    if (password && confirmPassword && password.value && confirmPassword.value && password.value !== confirmPassword.value) {
      confirmPassword.setErrors({ mismatch: true });
    } else {
      confirmPassword.setErrors(null);
    }
    
    return null;
  }

  register(): void {
    if (this.registrationForm.valid) {
      const userData: User = {
        email: this.registrationForm.value.email,
        password: this.registrationForm.value.password,
        username: this.registrationForm.value.username,
        mobileNumber: this.registrationForm.value.mobileNumber,
        role: this.registrationForm.value.role
      };
      this.authService.register(userData).subscribe({
        next: (registeredUser) => {
          this.successMessage = 'Registration Successful!';
          setTimeout(() => {
            this.router.navigate(['/login']);
          }, 2000);
        },
        error: (err) => {
          let errorMessage = 'An error occurred';
          if (err.status === 409) {
            errorMessage = 'User already exists';
          }
          this.errorMessage = errorMessage;
          this.showErrorModal();
        }
      });
    }
  }

  showErrorModal(): void {
    const errorModal = document.getElementById('errorModal') as HTMLDialogElement;
    if (errorModal) {
      errorModal.showModal();
    }
  }

  closeErrorModal(): void {
    const errorModal = document.getElementById('errorModal') as HTMLDialogElement;
    if (errorModal) {
      errorModal.close();
    }
  }
}
