import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { AdminService } from './admin.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  adminDetails: any[] = [];
  adminToUpdate = { id: null, name: '', email: '', phoneNumber: '' };
  showSuccessPopup: boolean = false;
  formSubmitted: boolean = false;    // Track submit press for validation display

  constructor(private adminService: AdminService) {}

  ngOnInit(): void {
    this.getAdminDetails();
  }

  // Fetch all admin details
  getAdminDetails(): void {
    this.adminService.getAdmin().subscribe(
      (resp: any) => {
        this.adminDetails = Array.isArray(resp) ? resp : [];
      },
      (err: any) => {
        console.error('Error fetching admin data:', err);
      }
    );
  }

  // Register new admin (with error display logic)
  register(form: NgForm): void {
    this.formSubmitted = true;             // Shows errors if invalid
    if (form.valid) {
      this.adminService.registerAdmin(form.value).subscribe(
        () => {
          this.getAdminDetails();
          this.showPopup();
          form.reset();
          this.formSubmitted = false;      // Hide errors after success
        },
        (err: any) => {
          console.error('Error registering admin:', err);
        }
      );
    }
    // If form invalid, errors automatically show!
  }

  // Populate form for editing
  edit(admin: any): void {
    this.adminToUpdate = { ...admin };
  }

  // Update admin record
  updateAdmin(): void {
    this.adminService.updateAdmin(this.adminToUpdate).subscribe(
      () => {
        this.getAdminDetails();
        this.showPopup();
      },
      (err: any) => {
        console.error('Error updating admin:', err);
      }
    );
  }

  // Delete admin record
  deleteAdmin(admin: any): void {
    if (confirm(`Are you sure you want to delete ${admin.name}?`)) {
      this.adminService.deleteAdmin(admin.id).subscribe(
        () => {
          this.getAdminDetails();
        },
        (err: any) => {
          console.error('Error deleting admin:', err);
        }
      );
    }
  }

  // Success popup animation
  private showPopup(): void {
    this.showSuccessPopup = true;
    setTimeout(() => {
      this.showSuccessPopup = false;
    }, 2000);
  }
}
