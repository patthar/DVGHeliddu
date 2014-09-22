use strict;
use warnings;

use HTTP::Tiny;
use HTML::Manipulator;
use JSON;
use Data::Dumper;
use DateTime;

my $http_obj = HTTP::Tiny->new();
my @data = ();
my $ctr=1;
open my $fh, "<", "contents" or die "$!: could not open";
open my $afh, ">", "kagga_data.json" or die "$!: could not open";
my $start_dt = DateTime->new( year => 2010, month => 12, day => 01);
my $end_dt = DateTime->new( year => 2013, month => 07, day => 01);


while($start_dt->ymd('') le $end_dt->ymd('')) {
    last;
    print "Processing ", $start_dt->ymd(''),"\n";
    my $url = 'http://wordsofwisdom.in/mankutimmanakagga/';
    $url .= join("/",$start_dt->year(), $start_dt->month(), $start_dt->day());
    print "Will extract $url\n";
    my $response = $http_obj->get($url);
    my $content = HTML::Manipulator::extract_content($response->{content},"content");
    &populate_kagga($content, \@data);
    #print $fh to_json({$ctr => $content});
    $start_dt->add(days => 1);
    $ctr++;
}

print "reading data\n";
my $file_data = <$fh>;
my $contents = from_json($file_data, {utf8 => 1});
print "has ", scalar keys %$contents;
foreach my $id (sort { $a <=> $b } keys %$contents) {
    print "Processing $id\n";
    &populate_kagga($contents->{$id}, \@data);
    $ctr++;
}
print $afh to_json(\@data, {pretty => 1});
close $fh;
close $afh;

sub populate_kagga {
    my $content = shift;
    my $data = shift;

    my $kagga;
    my $base_key = qq/kagga-$ctr/;
    $content =~ s/\n+//g;
    if($content =~ /(<h1 class="entry-title.*<a.*?>(.*)<\/a><\/h1>)/) {
	$kagga->{$base_key}->{title} = $2;
	$kagga->{$base_key}->{id} = $ctr;
    }

    if($content =~ /(<div class=.?"entry-content.?">(<.*?>){0,2}(.*?)<span class='st_facebook_large'.*)/) {
	print "pattern matched : $3\n";
	$kagga->{$base_key}->{content} = $3;
    }
    else {
	print "$ctr: did not match\n";
    }
    print "No content\n" if($kagga->{$base_key}->{content} eq "");
    push @$data, $kagga;
}

