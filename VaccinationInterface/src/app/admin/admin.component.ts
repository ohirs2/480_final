import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-admin',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './admin.component.html',
  styleUrl: './admin.component.css'
})
export class AdminComponent implements OnInit {
  ngOnInit(): void {
    this.http.get<any>('http://localhost:8080/nurse').subscribe(
      (response) => {
        this.nurses = response; // Assuming the response is an array of nurses
      },
      (error) => {
        console.error('Error fetching data:', error);
      }
    );
  }

  nurses: any[] = [];

  constructor(private http: HttpClient, private cd: ChangeDetectorRef) {}


  toggleEdit(nurse: any): void {
    nurse.isEditMode = !nurse.isEditMode;
    if (!nurse.isEditMode) {
      // If we're leaving edit mode, send the PUT request
      this.saveNurse(nurse);
    }
  }

  saveNurse(nurse: any): void {

    console.log(nurse)

    let post = {
      firstName: nurse.FirstName,
      middleInitial: nurse.MiddleInitial,
      lastName: nurse.LastName,
      employeeID: nurse.EmployeeID,
      gender: nurse.Gender,
      phoneNumber: nurse.PhoneNumber,
      address: nurse.Address
    }
    console.log(post)

      this.http.post('http://localhost:8080/nurse/insert', post).subscribe({
        next: (response) => {
        },
        error: (error) => {
        }
      });
    
  }
  
  

  addNurse(): void {
    const newNurse = {
      FirstName: '',
      MiddleInitial: '',
      LastName: '',
      EmployeeID: '',
      Gender: '',
      PhoneNumber: '',
      Address: '',
      isEditMode: true // Set this to true so you can edit immediately
    };
    this.nurses.push(newNurse); // Add a new nurse to the array
  }

  deleteNurse(index: number): void {
    const nurseToDelete = this.nurses[index];
    // If the nurse has an ID, we assume it's from the server and needs a DELETE request.
    if (nurseToDelete.EmployeeID) {
      this.http.delete('http://localhost:8080/nurse/delete/' + nurseToDelete.EmployeeID).subscribe({
        next: (response) => {
          // Remove the nurse from the array on successful response
        },
        error: (error) => {
          this.nurses = [
            ...this.nurses.slice(0, index),
            ...this.nurses.slice(index + 1)
          ];
          this.cd.detectChanges();
        }
      });
    } else {
      // If it doesn't have an ID, it was added in the client and can just be removed
      this.nurses.splice(index, 1);
    }
  }


}
