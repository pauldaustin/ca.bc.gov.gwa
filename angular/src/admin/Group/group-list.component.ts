import {
  Component,
  Injector,
  OnInit
} from '@angular/core';

import {BaseListComponent} from '../../shared/Component/BaseListComponent';

import {Group} from '../Group/Group';
import {GroupService} from './group.service';

@Component({
  selector: 'app-group-list',
  templateUrl: 'group-list.component.html'
})
export class GroupListComponent extends BaseListComponent<Group> implements OnInit {

  addGroupName: string;

  constructor(
    injector: Injector,
    protected groupService: GroupService
  ) {
    super(injector, groupService, 'Groups - Gateway Admin');
  }

  ngOnInit(): void {
    this.columns = [
      {prop: 'group', name: 'Group', cellTemplate: this.idTemplate, sortable: true},
    ];
    super.ngOnInit();
  }
}