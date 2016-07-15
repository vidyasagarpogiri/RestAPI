class BooksController < ApplicationController
  def show
  end

  # to get index of books
  def index
  @books = Book.all
  end

  def new
	@book = Book.new
  end

  def create
		@listing = current_user.listings.build(listing_params)

		if @listing.save
		   Notification.job_notification(@listing).deliver_now
			redirect_to @listing, notice: "Successfully created new Listing"
		else
			render 'new'
		end
	end

  def edit
  end

  def update
  end

  def destroy
  end
end
