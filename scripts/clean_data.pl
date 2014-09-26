use JSON;
use Data::Dumper;
use strict;
use warnings;

local $/ = undef;
open my $fh, "<", "kagga_data.json" or die;
open my $afh, ">", "kagga_data_split.json" or die;
open my $aafh, ">", "kagga_data_split_pretty.json" or die;
#open my $temp, ">", "kagga_data_temp.json" or die;
my $file_data = <$fh>;

my $ds = from_json($file_data, {utf8 => 1});
my $ctr=1;

#foreach my $kagga (@$ds) {
#   foreach my $key (keys %$kagga) {
#	my %val = %{$kagga->{$key}};
#	$val{id} = $ctr;
#	$kagga->{qq{pavan-$ctr}} = \%val;
#	delete $kagga->{$key};
#    }
#    $ctr++;
#}
#print $temp to_json($ds, {pretty => 1});
#close $temp;
#close $fh;
#exit;
my @new_arr;
foreach my $kagga (@$ds) {
    foreach my $key (keys %$kagga) {
	my $content = $kagga->{$key};
	if($content->{content} =~ m/(.*?)([a-zA-Z]+.*?[0-9]+.*?)([a-zA-Z]+.*$)/) {
	    $content->{kagga} = $1;
	    $content->{transliteration} = $2;
	    $content->{translation} = $3;
	} else {
	    print "$key : not matched\n";
	}
	push @new_arr, $content;
    }
}

print $afh to_json(\@new_arr);
print $aafh to_json(\@new_arr,{pretty => 1});
close $fh;
close $afh;
close $aafh;
