import {
  Component, 
  Injector
} from '@angular/core';
import { BaseDetailComponent } from '../Component/BaseDetailComponent';
import { Api } from './Api';
import { ApiService } from './ApiService';

@Component({
  selector: 'api-detail',
  templateUrl: 'ApiDetail.html'
})
export class ApiDetailComponent extends BaseDetailComponent<Api> {

  activeTab : string;
  
  constructor(
    protected injector:Injector,
    protected service: ApiService
  ) {
    super(injector, service);
    this.idParamName = 'name';
  }

  ngOnInit(): void {
    super.ngOnInit();
  }

}
