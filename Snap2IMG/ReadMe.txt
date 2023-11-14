

--- Snap2IMG ----------------------------------------------------------
 
  Freeware by RL Vision (c) 2013
  Homepage: http://www.rlvision.com
  
  Portable:
    - Just unzip and run
    - Settings are saved in the application folder

  Works on Windows XP and above. (If you get an error message "The 
  application failed to initialize properly (0xc0000135)" then
  you need to install .Net Framework 2.0 (or higher) from Microsoft.)

  AForge.Net provided under LGPL v.3 license. See "AForge License.txt" 
  for more info. 
 
--- About -------------------------------------------------------------

  Snap2IMG generates contact sheets (aka a thumbnail sheet or an image 
  index) for folders on your hard drive. The result is a larger image 
  containing thumbnails showing all the images in the folder. You can 
  process a single folder or a complete folder structure, 
  automatically generating a sheet for each subfolder. The sheets can
  be styled in various ways, including size and color settings, 
  showing filenames & a header & footer, and adding borders, shadows
  and textured backgrounds. Additionally, large folders can be split 
  into multiple sheets if wanted.


--- Scenarios ---------------------------------------------------------

  Viewing a collection of pictures as thumbnails is nothing new and has 
  its obvious advantages. Snap2IMG enhances this by saving the thumbnail 
  views as individual images and automating this process for complete 
  folder structures. Here are some scenarios where this can be useful:

    - Index image folders that you don't have readily accessible, for 
      example external harddrives and CD's.
      
    - Upload to forums to preview linked attachments.
    
    - Keep as a memory of image libraries you don't need or want 
      anymore, before you delete it.
      
    - Easily share thumbnails with friends
    
    - Upload contact sheets to your homepage

	- Create a showcase image for all icons in an icon pack


  Even if you have all the images available on your computer, it can 
  still be more convenient to use contact sheets instead of browsing the 
  folders "for real":

    - Each folder is "compressed" into a single file. Since folders 
      with many files can take a lot of time just to list, working 
      with contact sheets can be much quicker.
      
    - Easy to navigate: Use the next/previous shortcut keys in the 
      image viewer to "change" folder (Typically the PageUp/PageDown 
      keys).
      
    - Configure your contact sheets to show just the information you 
      are interested in.


--- How To Use --------------------------------------------------------

  Snap2IMG has been designed to be intuitive and self-descriptive, so 
  hopefully you should not have any problems using it. Thus I only 
  write some notes here on certain aspects of the program.


  Tooltips:

    Most controls have tooltips if you hold the mouse over them, showing 
	helpful information and tips.


  Modes:

    The first thing you do is to select a source folder to process. There 
    are two modes you can select here, and they differ a bit in how to use:
    
    1. "This folder only" simply creates a contact sheet of the 
       selected source folder. When clicking save you get to choose 
       a filename to save the contact sheet as.
        
    2. "This folder and each subfolder" creates an individual contact 
       sheet for the source folder and each subfolder (provided folders 
       contains images). Instead of specifying the filename to save as, 
       you specify the output folder where the generated images are to 
       be saved. The images are automatically named to reflect the 
       source folder, for example  "d:\photos\2012\" becomes a contact 
       sheet with the name of "d#photos#2012.jpg".


  Previews:
  
    The preview button produces the same result as the save button, but 
    instead of saving the generated image on disk it is displayed in a 
    preview window. This is convenient when testing various settings. 
    You can right click on the preview image to copy it to the clipboard. 
    Preview always acts as if "This folder only" is selected (see above).


  Presets:
  
    Using the preset menu you can save settings as named presets that you 
    can later recall. All design related settings are stored. Source settings 
    (selected folder, process mode) are not saved in presets though.


  Ignore Aspect Ratio:
  
    This options appears several times, most notably when selecting thumbnail 
    sizes. Think of it this way: Ignoring the aspect ratio will "fill" the 
    entire thumbnail size. Keeping the aspect ratio (default) will usually 
    look better, at the expence of adding more or less "white space". This 
    white space can be reduced by selecting a thumbnail size that corresponds 
    to the aspect ratio of the source images. (The default thumbnail size is 
    set to 128x96 which corresponds to a 4:3 aspect ratio, which is common 
    for photos.)


  Jpeg Image Quality:
  
    This program is built using .Net and uses the built in routines for saving 
    images. Unfortunately I have noticed that the jpg saver in .Net is not 
    the best. I have chosen to implement two quality settings when saving 
    jpg: "High" and "Medium". High is fairly good with a reasonable file 
    size, but it's not excellent and other jpg saver producer better quality. 
    For best quality, use the png image format instead, since it is lossless. 
    Then you can use another jpg saver with better quality to compress the 
    files if you want to.


  Font Color:
  
    The font selection dialog is also used to set text color. Unfortunately 
	it only offers a very limited set of basic colors. If you need some other
	color you should be able to edit the settings file directly if you don't 
	mind some "hacking" :-) (but if you change font settings inside the 
	program the color will reset).


--- Command Line ------------------------------------------------------

  Give a path as command line argument to set the root folder to this at 
  startup. Make sure to enclose it in "quotes" if the path contains spaces.

  Example:
  
  Snap2IMG.exe "c:\my pictures"


--- Version History ---------------------------------------------------

  v2.01 (2013-07-11 )
    Fixed some bugs when using thick borders
	Added 'Grayish' preset

  v2.0 (2013-07-09 )
	Multithreaded rendering engine for performance
	Split one folder into several sheets
	Adaptive thumbnail height
	Shadow and grayscale effects
	Long filenames can be truncated
	Filename background color
	Text antialiasing quality in menu
	Added 'Black Box' preset
	Less memory hungry
	Better error handling
	Many bug fixes and minor tweaks

  v1.01 (2013-03-27)
	Fixed a bug preventing from saving images...

  v1.0 (2013-03-25)
	Initial release


--- End Use License Agreement -----------------------------------------
  
  1. License
  
  By receiving and/or using RL Vision software, you accept the following
  User Agreement. This agreement is a binding legal agreement between RL
  Vision and the users of RL Vision's software and products. IF YOU DO NOT
  INTEND TO HONOR THIS AGREEMENT, DELETE THIS SOFTWARE NOW.
  
  2. Distribution
  
  This freeware software may be freely distributed, provided that:
  
    1. Such distribution includes only the original archive supplied by RL
    Vision. You may not alter, delete or add any files in the distribution
    archive.
  
    2. No money is charged to the person receiving the software, beyond
    reasonable cost of packaging and other overhead.
  
  3. User Agreement
  
  3.1. Usage and distribution restrictions
  
  The user may not use or distribute RL Vision's software for any unlawful
  purpose.
  
  The user is not allowed to attempt to reverse engineer, disassemble or
  decompile RL Vision Software and products.
  
  3.2 Copyright restriction
  
  All parts of RL Vision software and products are copyright protected
  unless otherwise stated. No program, code, part, image, video clip,
  audio sample, text or computer generated sequence of images may be
  copied or used in any way by the user except as intended within the
  bounds of the single user program.
  
  3.3. Limitation of responsibility
  
  The user of RL Vision software will indemnify, hold harmless, and defend
  RL Vision against lawsuits, claims, costs associated with defense or
  accusations that result from the use of RL Vision software.
  
  RL Vision is not responsible for any damages whatsoever, including loss
  of information, interruption of business, personal injury and/or any
  damage or consequential damage without limitation, incurred before,
  during or after the use of our products.
  
