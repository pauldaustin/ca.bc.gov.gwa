import 'rxjs/add/operator/toPromise';
import {
  Injectable,
  Injector
} from '@angular/core';
import { BaseService } from '../../../shared/Service/BaseService';
import { Group } from '../../Group/Group';

@Injectable()
export class ApiGroupUserService extends BaseService<Group> {
  constructor(injector: Injector) {
    super(injector);
    this.typeTitle = 'Group';
    this.labelFieldName = 'group';
  }

  deleteObject(group: Group, path?: string): Promise<boolean> {
    return this.deleteObjectDo(path + '/' + group.username);
  }

  newObject(): Group {
    return new Group();
  }
}