import { ApplicationShell } from '@theia/core/lib/browser';
import { injectable, postConstruct } from '@theia/core/shared/inversify';

@injectable()
export class CustomizationApplicationShell extends ApplicationShell {

    @postConstruct()
    protected init(): void {
        super.init();

        // this.collapsePanel('right');
        // this.expandPanel('left');

        const div = document.createElement('div');
        div.id = 'example-header';

        const h1 = document.createElement('h1');
        h1.id = 'example-app-title';
        const appTitle = document.createTextNode('Eclipse Theia Example Application');
        h1.append(appTitle);
        div.append(h1)

        document.body.prepend(div);
        document.body.prepend(this.createStyle());
    }

    /**
     * TODO: Here we need the !important statements allthough this is a worst case solution. Need to check, if Theia vars need to be set in a separate theme JSON
     * @returns a <style> node that contains the CSS as text node
     */
    private createStyle(): HTMLElement {
        const style = document.createElement('style');
        const css = document.createTextNode(`
            :root {
                --brand-color1: rgb(53, 52, 52);
                --brand-color2: rgb(218, 122, 8);

                --theia-ui-font-family: 'Roboto','Segoe UI','Helvetica','Arial',sans-serif;
                --theia-ui-font-size1: 14px;
                --theia-activityBar-foreground: var(--brand-color1) !important;
                --theia-activityBar-activeBorder: var(--brand-color1) !important;
                --theia-activityBar-activeBackground: var(--theia-sideBar-background);
                --theia-statusBar-noFolderBackground: #6A737C !important;
                --1theia-list-inactiveSelectionBackground : #e7cde4 !important;
                --theia-list-activeSelectionBackground: var(--brand-color1) !important;
            }

            #theia-app-shell {
                top: 2.8rem;
            }

            #theia-statusBar {
                background: #164293;
            }

            #example-header {
                position: fixed;
                top: 0;
                left: 0;
                right: 0;
                height: 2.8rem;
                display: flex;
                justify-content: space-between;
                align-items: center;
                background: linear-gradient(to right,var(--brand-color1),var(--brand-color2));
                color: white;
                padding: 0 1rem;
            }

            #example-app-title {
                margin: 0;
                font-size: 1.33rem;
                font-weight: 300;
                text-transform: uppercase;
            }`);
        style.append(css);
        return style;
    }
}