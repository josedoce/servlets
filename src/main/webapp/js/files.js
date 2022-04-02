/**
 * @author https://javacodepoint.com/ajax-file-upload-with-advance-progress-bar-in-java
 * @testedby josedoce
 */
 
/* Globle variables */
    let totalFileCount, fileUploadCount, fileSize;
 
    /* start uploading files */
    function startUploading() {
        let files = document.getElementById('files').files;
        if(files.length==0){
            alert("Please choose at least one file and try.");
            return;
        }
        fileUploadCount=0;
        prepareProgressBarUI(files);
         
        // upload through ajax call     
        uploadFile();
    }
     
    /* This method will be called to prepare progress-bar UI */
    function prepareProgressBarUI(files){
        totalFileCount = files.length;
        let $tbody=$("#progress-bar-container").find("tbody");
        $tbody.empty();
        $("#upload-header-text").html("1 of "+totalFileCount+" file(s) is uploading");
        for(let i=0;i<totalFileCount;i++){
            let fsize=parseFileSize(files[i].size);
            let fname=files[i].name;
            let bar='<tr id="progress-bar-'+i+'"><td style="width:75%"><div class="filename">'+fname+'</div>'
            +'<div class="progress"><div class="progress-bar progress-bar-striped active" style="width:0%"></div></div></td>'
            +'<td  style="width:25%"><span class="size-loaded"></span> '+fsize+' <span class="percent-loaded"></span></td></tr>';
            $tbody.append(bar);
        }
        $("#upload-status-container").show();
    }
     
    /* parse the file size in kb/mb/gb */
    function parseFileSize(size){
        let precision=1;
        let factor = Math.pow(10, precision);
        size = Math.round(size / 1024); //size in KB
        if(size < 1000){
            return size+" KB";
        }else{
            size = Number.parseFloat(size / 1024); //size in MB
            if(size < 1000){
                return (Math.round(size * factor) / factor) + " MB";
            }else{
                size = Number.parseFloat(size / 1024); //size in GB
                return (Math.round(size * factor) / factor) + " GB";
            }
        }
        return 0;
    }
 
    /* one by one file will be uploaded to the server by ajax call*/
    function uploadFile() {
        let file = document.getElementById('files').files[fileUploadCount];
        fileSize = file.size;
        let xhr = new XMLHttpRequest();
        let fd = new FormData();
        fd.append("multipartFile", file);
        xhr.upload.addEventListener("progress", onUploadProgress, false);
        xhr.addEventListener("load", onUploadComplete, false);
        xhr.addEventListener("error", onUploadError, false);
        xhr.open("POST", "EnvioDeArquivoServlet");
        xhr.send(fd);
         
    }
 
    /* This function will continueously update the progress bar */
    function onUploadProgress(e) {
        if (e.lengthComputable) {
            let percentComplete = parseInt((e.loaded) * 100 / fileSize);
            let pbar = $('#progress-bar-'+fileUploadCount);
            let bar=pbar.find(".progress-bar");
            let sLoaded=pbar.find(".size-loaded");
            let pLoaded=pbar.find(".percent-loaded");
            bar.css("width",percentComplete + '%');
            sLoaded.html(parseFileSize(e.loaded)+ " / ");
            pLoaded.html("("+percentComplete+ "%)");
        } else {
            alert('unable to compute');
        }
    }
 
    /* This function will call when upload is completed */
    function onUploadComplete(e, error) {
        let pbar = $('#progress-bar-'+fileUploadCount);
        if(error){
            pbar.find(".progress-bar").removeClass("active").addClass("progress-bar-danger");
        }else{
            pbar.find(".progress-bar").removeClass("active");
            pbar.find(".size-loaded").html('<i class="fa fa-check text-success"></i> ');
        }
        fileUploadCount++;
        if (fileUploadCount < totalFileCount) {
            //ajax call if more files are there 
            uploadFile();
            $("#upload-header-text").html((fileUploadCount+1)+" of "+totalFileCount+" file(s) is uploading");
        } else {
            $("#upload-header-text").html("File(s) uploaded successfully!");
        }
    }
 
    /* This function will call when an error come while uploading */
    function onUploadError(e) {
        console.error("Something went wrong!");
        onUploadComplete(e,true);
    }
     
    function showHide(ele){
        if($(ele).hasClass('fa-window-minimize')){
            $(ele).removeClass('fa-window-minimize').addClass('fa-window-restore').attr("title","restore");
            $("#progress-bar-container").slideUp();
        }else{
            $(ele).addClass('fa-window-minimize').removeClass('fa-window-restore').attr("title","minimize");
            $("#progress-bar-container").slideDown();
        }
    }
