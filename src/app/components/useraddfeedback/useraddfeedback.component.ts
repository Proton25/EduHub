import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
// import { log } from 'console';
// import { take } from 'rxjs/operators';
import { Feedback } from 'src/app/models/feedback.model';
import { AuthService } from 'src/app/services/auth.service';
import { FeedbackService } from 'src/app/services/feedback.service';

@Component({
  selector: 'app-useraddfeedback',
  templateUrl: './useraddfeedback.component.html',
  styleUrls: ['./useraddfeedback.component.css']
})
export class UseraddfeedbackComponent implements OnInit {
  feedbackForm: FormGroup;
  showPopup: boolean = false;
  userId: number | null = null;

  constructor(
    private feedbackService: FeedbackService,
    private authService: AuthService,
    private formBuilder: FormBuilder,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.userId=this.authService.getUserIdd();
    this.feedbackForm = this.formBuilder.group({
      feedbackText: ['', Validators.required]
    });
  }

  addFeedback(): void {
    if (this.feedbackForm.valid) {

      const feedback: Feedback = {
        feedbackText: this.feedbackForm.value.feedbackText,
        date: new Date(),
        userId: this.userId
      };
      console.log("HIIIII "+JSON.stringify(feedback));
      this.feedbackService.sendfeedback(feedback).subscribe(() => {
        this.showPopup = true;
        this.feedbackForm.reset();
      }, error => {
        console.error("Error adding feedback", error);
        alert("Error adding feedback. Please try again.");
      });
    } else {
      alert("Invalid Feedback.");
    }
  }

  closePopup(): void {
    this.showPopup = false;
  }
}