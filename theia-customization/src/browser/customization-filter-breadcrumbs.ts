import { Breadcrumb, BreadcrumbsService } from '@theia/core/lib/browser';
import URI from '@theia/core/lib/common/uri';
import { injectable } from '@theia/core/shared/inversify';

@injectable()
export class BreadcrumbsFilterService extends BreadcrumbsService {

    async getBreadcrumbs(uri: URI): Promise<Breadcrumb[]> {
        // always return empty array to avoid to show a breadcrumb
        return [];
    }
}