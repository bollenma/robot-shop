import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {Robot} from '../../core/robot.model';
import {isNullOrUndefined} from 'util';
import {URL_SERVER, API_ROBOTS} from '../../constants/ConstantsURL';
import 'rxjs/add/operator/map';
import {Page} from '../../core/page.model';

@Injectable()
export class RobotService {
  
  url = URL_SERVER + API_ROBOTS;
  
  constructor(private http: HttpClient) {
  }
  
  private static handleError(error: any): Observable<any> {
    console.error('An error occurred', error);
    return Observable.throw(error.message || error);
  }
  
  findAllPaginated(): Observable<Page<Robot>> {
    return this.http.get<Page<Robot>>(this.url)
      .map(
        response => response,
        error => RobotService.handleError(error),
      );
  }
  
  find(id: number): Observable<Robot> {
    return this.http.get<Robot>(this.url + '/' + id)
      .map(
        response => response,
        error => RobotService.handleError(error),
      );
  }
  
  findByModelPaginated(model: string): Observable<Page<Robot>> {
    return this.http.get(this.url + '/model/' + model)
      .map(
        response => response as Page<Robot>,
        error => RobotService.handleError(error),
      );
    
  }
  
  search(search: string): Observable<Page<Robot>> {
    return this.http.get(this.url + '/search', {params: {query: search}})
      .map(
        response => response as Page<Robot>,
        error => RobotService.handleError(error),
      );
  }
  
  searchWithModel(search: string, model: string): Observable<Page<Robot>> {
    return this.http.get(this.url + '/search', {params: {query: search, model: model}})
      .map(
        response => response as Page<Robot>,
        error => RobotService.handleError(error),
      );
  }
  
  create(robot: Robot): Observable<Robot> {
    return this.http.post(this.url, robot)
      .map(
        response => response as Robot,
        error => RobotService.handleError(error),
      );
  }
  
  update(robot: Robot): Observable<Robot> {
    return this.http.put(this.url, robot)
      .map(
        response => response as Robot,
        error => RobotService.handleError(error),
      );
    
  }
  
  remove(id: number) {
    this.http.delete(this.url + '/' + id).subscribe(
      response => console.log(response),
      error => RobotService.handleError(error),
    );
  }
  
}
