package example.grails

import grails.config.Config
import grails.core.support.GrailsConfigurationAware
import groovy.transform.CompileStatic

import static org.springframework.http.HttpStatus.OK

@CompileStatic
class ExcelController implements GrailsConfigurationAware {

    BookService bookService
    BookExcelService bookExcelService

    String xlsxMimeType
    String encoding

    @Override
    void setConfiguration(Config co) {
        xlsxMimeType = co.getProperty('grails.mime.types.xlsxMimeType',
                String,
                'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet')
        encoding = co.getProperty('grails.converters.encoding', String, 'UTF-8')
    }

    def index() {
        response.status = OK.value()
        response.setHeader "Content-disposition", "attachment; filename=${BookExcelService.EXCEL_FILENAME}"
        response.contentType = "${xlsxMimeType};charset=${encoding}"
        OutputStream outs = response.outputStream
        bookExcelService.exportExcelFromBooks(outs, bookService.findAll())
        outs.flush()
        outs.close()
    }
}
