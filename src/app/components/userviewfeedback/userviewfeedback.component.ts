import { Component, OnInit } from '@angular/core';
import { Feedback } from 'src/app/models/feedback.model';
import { AuthService } from 'src/app/services/auth.service';
import { FeedbackService } from 'src/app/services/feedback.service';

@Component({
  selector: 'app-userviewfeedback',
  templateUrl: './userviewfeedback.component.html',
  styleUrls: ['./userviewfeedback.component.css']
})
export class UserviewfeedbackComponent implements OnInit {
  feedbacks: any[] = [];
  showPopup: boolean = false;
  feedbackIdToDelete: number | null = null;
  userId: number | null = null;
  isLoading: boolean = true;

  constructor(
    private feedbackService: FeedbackService,
    private authService: AuthService
  ) { }

  ngOnInit(): void {
    this.userId = this.authService.getUserIdd();
    this.getFeedbacksByUserId(this.userId);
  }

  getFeedbacksByUserId(userId: number): void {
    this.feedbackService.getAllFeedbacksByUserId(userId).subscribe(data => {
      this.feedbacks = data;
      this.isLoading = false;
    });
  }

  confirmDelete(feedbackId: number): void {
    this.feedbackIdToDelete = feedbackId;
    this.showPopup = true;
  }

  deleteFeedback(): void {
    if (this.feedbackIdToDelete !== null) {
      this.feedbackService.deleteFeedback(this.feedbackIdToDelete).subscribe(() => {
        if (this.userId !== null) {
          this.getFeedbacksByUserId(this.userId); // Refresh feedbacks after deletion
        }
        this.closePopup();
      });
    }
  }

  closePopup(): void {
    this.showPopup = false;
    this.feedbackIdToDelete = null;
  }
}
