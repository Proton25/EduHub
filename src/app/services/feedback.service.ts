import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Feedback } from '../models/feedback.model';

 
@Injectable({
  providedIn: 'root'
})
export class FeedbackService {
 
    // API endpoint for feedback-related operations
  public apiUrl: string = "https://8080-ebbdcaaaebadcfedfdbdcbbaecbbddb.premiumproject.examly.io/api/feedback"
 
  constructor(private readonly http: HttpClient) { }
 
  /**
   * Sends feedback.
   * @param feedback - Feedback object to be sent.
   * @returns Observable of the sent Feedback object.
   */
  sendfeedback(feedback: Feedback): Observable<Feedback> {
    return this.http.post<Feedback>(this.apiUrl, feedback)
  }
 
  /**
   * Fetches all feedbacks by user ID.
   * @param userId - ID of the user whose feedbacks are to be fetched.
   * @returns Observable of an array of Feedback objects.
   */
  getAllFeedbacksByUserId(userId: number): Observable<Feedback[]> {
    return this.http.get<Feedback[]>(`${this.apiUrl}/user/${userId}`)
  }
 
  /**
   * Deletes a feedback by its ID.
   * @param feedbackId - ID of the feedback to delete.
   * @returns Observable of an array of Feedback objects.
   */
  deleteFeedback(feedbackId: number): Observable<Feedback[]> {
    return this.http.delete<Feedback[]>(`${this.apiUrl}/${feedbackId}`)
  }
 
  /**
   * Fetches all feedbacks.
   * @returns Observable of an array of Feedback objects.
   */
  getFeedbacks(): Observable<Feedback[]> {
    return this.http.get<Feedback[]>(this.apiUrl)
  }
 


}
 