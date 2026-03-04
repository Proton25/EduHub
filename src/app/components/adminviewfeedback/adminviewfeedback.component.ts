import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user.model';
import { AuthService } from 'src/app/services/auth.service';
import { FeedbackService } from 'src/app/services/feedback.service';
 
@Component({
  selector: 'app-adminviewfeedback',
  templateUrl: './adminviewfeedback.component.html',
  styleUrls: ['./adminviewfeedback.component.css']
})
export class AdminviewfeedbackComponent implements OnInit {
  feedbacks: any[] = [];
  showPopup: boolean = false;
  selectedUser: User | null = null;
  feedbackIdToDelete: number | null = null;

  showUserDetails(user: User): void {
    this.selectedUser = user;
    this.showPopup = true;
  }

  closePopup(): void {
    this.showPopup = false;
    this.selectedUser = null;
  }

  getAllFeedbacks() {
    this.feedbackService.getFeedbacks().subscribe((data) => {
      this.feedbacks = data;
      this.feedbacks.forEach(feedback => {
        this.getUserDetailsById(feedback.userId).then(user => {
          feedback.user = user;
        });
      });
    });
  }

  getUserDetailsById(userId: number): Promise<User> {
    return new Promise((resolve) => {
      this.authService.getUserDetails(userId).subscribe((data) => {
        resolve(data);
      });
    });
  }
  
  constructor(private feedbackService: FeedbackService, private authService:AuthService) { }
 
  ngOnInit(): void {
    this.getAllFeedbacks();
  }
 
}
 